(ns euler-clojure.problem54)

(defn parse-card
  [card]
  (let [[value suit] card
        suit (case suit \H :hearts \C :clubs \D :diamonds \S :spades)
        value (case value \T 10 \J 11 \Q 12 \K 13 \A :ace (Character/getNumericValue value))]
    {:suit suit :value value}))

(defn royal-flush?
  [cards]
  (when (some #(= #{10 11 12 13 :ace} (set (map :value (second %)))) (group-by :suit cards))
    10000000000000000000000000000N))

(defn max-value
  [cards]
  (apply max (map #(condp = % :ace 14 %) (map :value cards))))

(defn flush?
  [cards]
  (when (= 1 (count (set (map :suit cards))))
    (* 10000000000N (max-value cards))))

(defn ace?
  [cards]
  (when (seq (filter (partial = :ace) (map :value cards)))
    (filter #(not= :ace (:value %)) cards)))

(defn max-ace
  [v]
  (condp = v :ace 14 v))

(defn straight?
  [cards]
  (when
    (let [ace? (ace? cards)
          straight? (fn [cs] (= (range 5) (sort (map #(- (:value %) (apply min (map :value cs))) cs))))]
      (if ace?
        (or (straight? (conj ace? {:value 1})) (straight? (conj ace? {:value 14})))
        (straight? cards)))
    (* 100000000N (max-value cards))))

(defn straight-flush?
  [cards]
  (when (and (flush? cards) (straight? cards))
    (* 10000000000000000N (max-value cards))))

(defn four-kind?
  [cards]
  (when-some [ts (seq (filter #(= 4 (count (second %))) (group-by :value cards)))]
    (+ (* 100000000000000N (max-ace (first (first ts)))) (max-value cards))))

(defn three-kind?
  [cards]
  (when-some [ts (seq (filter #(= 3 (count (second %))) (group-by :value cards)))]
    (+ (* 1000000N (max-ace (first (first ts)))) (max-value cards))))

(defn two-pairs?
  [cards]
  (let [pairs (filter #(>= (count (second %)) 2) (group-by :value cards))]
    (when-let [_ (= 2 (count pairs))]
      (+ (* 10000N (apply max (map (comp max-ace first) pairs))) (* 100 (apply min (map (comp max-ace first) pairs))) (max-value cards)))))

(defn pair?
  [cards]
  (let [pairs (filter #(= (count (second %)) 2) (group-by :value cards))]
    (when-let [_ (= (count pairs) 1)]
      (+ (* 100N (max-ace (first (first pairs)))) (max-value cards)))))

(defn full-house?
  [cards]
  (let [three (three-kind? cards)
        pair (pair? cards)]
    (when (and three pair)
      (+ (* 1000000000000N three) (* 100 pair) (max-value cards)))))

(defn high-card?
  [cards]
  (max-value cards))

(defn score-hand
  [cards]
  (or
    (royal-flush? cards)
    (straight-flush? cards)
    (four-kind? cards)
    (full-house? cards)
    (flush? cards)
    (straight? cards)
    (three-kind? cards)
    (two-pairs? cards)
    (pair? cards)
    (high-card? cards)))

(defn solve
  []
  (let [p1-wins? #(> (score-hand (take 5 %)) (score-hand (drop 5 %)))]
    (count (filter identity
            (map #(p1-wins? (map parse-card (clojure.string/split % #" ")))
                 (clojure.string/split-lines (slurp "resources/p054_poker.txt")))))))
