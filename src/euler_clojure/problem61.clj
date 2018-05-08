(ns euler-clojure.problem61)

(def tris (map (comp #(/ (* % (inc %)) 2) inc) (range)))
(def squares (map (comp #(* % %) inc) (range)))
(def pents (map (comp #(/ (* % (dec (* 3 %))) 2) inc) (range)))
(def hexs (map (comp #(* % (dec (* 2 %))) inc) (range)))
(def hepts (map (comp #(/ (* % (- (* 5 %) 3)) 2) inc) (range)))
(def octs (map (comp #(* % (- (* 3 %) 2)) inc) (range)))

(defn num->list
  [n]
  (map #(Character/getNumericValue %) (str n)))

(defn list->num
  [xs]
  (reduce (fn [acc x] (+ (* 10 acc) x)) 0 xs))

(defn get-ndigits
  [n xs]
  (take-while #(< (count (str %)) (inc n)) (drop-while #(< (count (str %)) n) xs)))

(defn cycle-map
  [xs]
  (reduce
    (fn [m [k v]] (assoc m k (conj (m k) v)))
    {}
    (map #(let [xs (pad 4 (num->list %))] [(list->num (take 2 xs)) (list->num (take-last 2 xs))]) xs)))

(defn pad
  [n xs]
  (if (< (count xs) n)
    (concat (repeat (- n (count xs)) 0) xs)
    xs))

(defn join
  [x y]
  (list->num (concat (pad 2 (num->list x)) (pad 2 (num->list y)))))

(defn search
  [goal candidates current path]
  (let [last-target (join current (list->num (take 2 (num->list goal))))]
    (if (= 1 (count candidates))
      (when (((first (vals candidates)) :set) last-target) [(conj path last-target)])
      (apply concat
             (map (fn [[k {mmap :map}]] (apply concat (keep #(search goal (dissoc candidates k) % (conj path (join current %))) (mmap current))))
                  candidates)))))

(defn solve
  []
  (let [tris (get-ndigits 4 tris)
        squares (get-ndigits 4 squares)
        pents (get-ndigits 4 pents)
        hexs (get-ndigits 4 hexs)
        hepts (get-ndigits 4 hepts)
        octs (get-ndigits 4 octs)
        triset (set tris)
        squareset (set squares)
        pentset (set pents)
        hexset (set hexs)
        heptset (set hepts)
        octset (set octs)
        trimap (cycle-map tris)
        squaremap (cycle-map squares)
        pentmap (cycle-map pents)
        hexmap (cycle-map hexs)
        heptmap (cycle-map hepts)
        octmap (cycle-map octs)]
    (mapcat #(search % {:squares {:map squaremap :set squareset}
                        :pents {:map pentmap :set pentset}
                        :hexs {:map hexmap :set hexset}
                        :heps {:map heptmap :set heptset}
                        :octs {:map octmap :set octset}}
                     (list->num (take-last 2 (num->list %))) [%]) tris)))
