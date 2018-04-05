(ns euler-clojure.core (:gen-class)
  (:require [euler-clojure.problem67 :as p]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main [& args]
  (p/solve 100 p/nums))
