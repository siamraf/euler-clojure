(ns euler-clojure.problem22)

(def letters "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(def letter-scores (zipmap letters (map inc (range))))

(defn word-value
  [word]
  (apply + (map #(letter-scores %) word)))

(defn solve
  []
  (let [names (sort (map #(second (clojure.string/split % #"\""))
                         (clojure.string/split (slurp "resources/p022_names.txt") #",")))
        rankings (zipmap names (map inc (range)))]
    (apply + (map #(* (rankings %) (word-value %)) names))))
