(ns euler-clojure.problem59)

(def cipher (map #(Integer/parseInt %) (clojure.string/split (clojure.string/trim (slurp "resources/p059_cipher.txt")) #",")))

(defn int->bytes
  [n]
  ((comp reverse last)
    (reduce
      (fn [[n acc] x]
        (if (> x n) [n (cons 0 acc)] [(- n x) (cons 1 acc)]))
      [n []]
      (reverse (take-while #(<= % n) (map #(apply * (repeat % 2)) (range)))))))

(defn bytes->int
  [bs]
  (apply + (map * (reverse bs) (map #(apply * (repeat % 2)) (range)))))

; turns out that these aren't needed :(

(def target-words
  (let [words #{" the " " and " " a " " or " " this " " that " " he " " she " " it "}]
    (set (concat words (map clojure.string/upper-case words)))))

(defn decrypt
  [cs]
  (let [out (map bit-xor cipher (apply concat (repeat cs)))]
    [(apply str (map char out)) (apply + out)]))

(defn solve
  []
  (doseq [c1 (range 32 125)
        c2 (range 32 125)
        c3 (range 32 125)]
    (let [[decrypted sum] (decrypt [c1 c2 c3])]
      (when (> (count (filter #(clojure.string/includes? decrypted %) target-words)) 2)
        (print decrypted)
        (print sum)))))
