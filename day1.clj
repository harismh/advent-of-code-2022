(ns com.harismh.advent-of-code-2022.day1
  (:require [clojure.string :as s]
            [com.harismh.advent-of-code-2022.test :refer [defpure run-tests]]))

(def input
  "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

(defpure parse-input
  {[input] [[1000 2000 3000] [4000] [5000 6000] [7000 8000 9000] [10000]]}
  [^String s]
  (for [line (s/split s #"\n *\n *")]
    (map parse-long (s/split line #"\n *"))))

(defpure max-elf-calories
  {[input] 24000}
  [^String s]
  (->> 
    (for [calories (vectorize-input s)]
      (reduce + 0 calories))
    (apply max)))

(defpure top-three-elf-calories
  {[input] 45000}
  [^String s]
  (->> 
    (for [calories (parse-input s)]
      (reduce + 0 calories))
    (sort-by -)
    (transduce (take 3) + 0)))

(comment 
 (run-tests))