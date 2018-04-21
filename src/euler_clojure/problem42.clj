(ns euler-clojure.problem42)

(defn triangles
  [n]
  (set (map #(/ (* % (inc %)) 2) (range 1 (inc n)))))

(def alpha-score (zipmap "\"ABCDEFGHIJKLMNOPQRSTUVWXYZ" (range 27)))

(defn word->score
  [word]
  (apply + (map #(alpha-score %) word)))

(defn solve
  []
  (let [tris (triangles 1000)]
    (count
      (filter #(tris (word->score %))
              (clojure.string/split (slurp "resources/p042_words.txt") #",")))))
