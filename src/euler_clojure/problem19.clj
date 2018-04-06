(ns euler-clojure.problem19)

(defn leap-year?
  [year]
  (or (= 0 (mod year 400))
      (and (not= 0 (mod year 100))
           (= 0 (mod year 4)))) )

(defn num-days-in-month
  [month year]
  (case month
    9 30
    4 30
    6 30
    11 30
    2 (if (leap-year? year) 29 28)
    31))

(def days
  [:mon :tues :wed :thurs :fri :sat :sun])

(defn days-in-month
  [month year]
  (map #(array-map :day (inc %) :month month :year year) (range (num-days-in-month month year))))

(defn days-in-year
  [year]
  (mapcat #(days-in-month (inc %) year) (range 12)))

(def weekday-start-date {:day 1 :month 1 :year 1900})

(defn dates-until
  [start-date end-date]
  (let [{:keys [day month year]} end-date
        days-of-week (apply concat (repeat days))]
    (take-while #(not= end-date (dissoc % :weekday))
                (drop-while #(not= start-date (dissoc % :weekday))
                            (map #(assoc %1 :weekday %2)
                                 (mapcat days-in-year (iterate inc (:year weekday-start-date)))
                                 days-of-week)))))

(defn solve
  []
  (let [start-date {:day 1 :month 1 :year 1901}
        end-date {:day 1 :month 1 :year 2001}
        all-days (dates-until start-date end-date)]
    (count (filter #(and (= :sun (:weekday %)) (= 1 (:day %))) all-days))))
