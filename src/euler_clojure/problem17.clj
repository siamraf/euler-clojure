(ns euler-clojure.problem17)

(defn units
  [n]
  (case n
    0 nil
    1 "one"
    2 "two"
    3 "three"
    4 "four"
    5 "five"
    6 "six"
    7 "seven"
    8 "eight"
    9 "nine"))

(defn tens
  [n]
  (case n
    0 nil
    1 nil
    2 "twenty"
    3 "thirty"
    4 "forty"
    5 "fifty"
    6 "sixty"
    7 "seventy"
    8 "eighty"
    9 "ninety"))

(defn ten->twenty
  [unit]
  (case unit
    0 "ten"
    1 "eleven"
    2 "twelve"
    3 "thirteen"
    4 "fourteen"
    5 "fifteen"
    6 "sixteen"
    7 "seventeen"
    8 "eighteen"
    9 "nineteen"))

(defn hundreds
  [n]
  (if (zero? n) nil (str (units n) " hundred and")))

; assumes that the number is <= 1000
(defn int->word
  [n]
  (cond
    (= n 1000) "one thousand"
    (= 0 (mod n 100)) (str (units (quot n 100)) " hundred")
    :else (let [nums (map #(Character/getNumericValue %) (str n))
                padded-nums (if (< (count nums) 3) (concat (repeat (- 3 (count nums)) 0) nums) nums)
                units (units (nth padded-nums 2))
                ten->twenty? (= 1 (nth padded-nums 1))
                tens (if ten->twenty? (ten->twenty (nth padded-nums 2)) (tens (nth padded-nums 1)))
                hundreds (hundreds (nth padded-nums 0))
                components (remove nil? (concat [hundreds] (if ten->twenty? [tens] [tens units])))]
            (clojure.string/join " " components))))

(defn solve
  [n]
  (count (filter (partial not= \ ) (apply concat (map int->word (range 1 (inc n)))))))
