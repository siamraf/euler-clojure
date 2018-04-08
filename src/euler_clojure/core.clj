(ns euler-clojure.core (:gen-class))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main [& args]
  (foo 1))
