(ns euler-clojure.problem18
  (:require  [ubergraph.core :as uber]
             [ubergraph.alg :as alg]))

; trick is to treat it like dijkstra

(def graph2
  (uber/digraph
    [:0 :10 -7]
    [:0 :01 -4]
    [:10 :100 -2]
    [:10 :010 -4]
    [:01 :001 -6]
    [:01 :010 -4]
    [:100 :1000 -8]
    [:100 :0100 -5]
    [:010 :0100 -5]
    [:010 :0010 -9]
    [:001 :0010 -9]
    [:001 :0001 -3]))

(def ex-nums
  [3
   7 4
   2 4 6
   8 5 9 3])

(def nums
  [75
   95 64
   17 47 82
   18 35 87 10
   20 04 82 47 65
   19 01 23 75 03 34
   88 02 77 73 07 63 67
   99 65 04 28 06 16 70 92
   41 41 26 56 83 40 80 70 33
   41 48 72 33 47 32 37 16 94 29
   53 71 44 65 25 43 91 52 97 51 14
   70 11 33 28 77 73 17 78 39 68 17 57
   91 71 52 38 17 14 91 43 58 50 27 29 48
   63 66 04 68 89 53 67 30 73 16 69 87 40 31
   04 62 98 27 23 9 70 98 73 93 38 53 60 04 23])

(defn get-children
  [nums row i]
  (let [parent (+ i (/ (* row (dec row)) 2))
        left-child (+ i (/ (* row (inc row)) 2))
        right-child (+ i 1 (/ (* row (inc row)) 2))]
    [[(keyword (str parent)) (keyword (str left-child)) (- (nums left-child))]
     [(keyword (str parent)) (keyword (str right-child)) (- (nums right-child))]]))

(defn get-edges
  [rows nums]
  (mapcat #(apply (partial get-children nums) %) (mapcat (fn [row] (map #(vector row %) (range row))) (range 1 rows))))

(defn solve
  [num-rows nums]
  (let [graph (apply uber/digraph (get-edges num-rows nums))
        last-row-start (/ (* num-rows (dec num-rows)) 2)]
       (+ (first nums)
          (- (apply min (map #(:cost (alg/shortest-path graph {:start-node :0 :end-node (keyword (str %)) :cost-attr :weight}))
                              (range last-row-start (+ last-row-start num-rows))))))))
