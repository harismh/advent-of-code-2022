(ns com.harismh.advent-of-code-2022.day4
  (:require [com.harismh.advent-of-code-2022.test :refer [defpure run-tests]]
   		      [clojure.string :as s]
   		      [clojure.set :as set]))

(def input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

(defpure parse-input
  {["2-4,6-8\n2-3,4-5"] [["2-4" "6-8"] ["2-3" "4-5"]]}
  [^String str]
  (for [line (s/split str #"\n")]
    (s/split line #",")))

(defpure shift-to-range
  {["2-4"] '(2 3 4)
   ["7-9"] '(7 8 9)}
  [^String shift]
  (let [start (Integer/parseInt (subs shift 0 1))
        end   (Integer/parseInt (subs shift 2 3))]
    (range start (inc end))))

(defpure sort-shifts
  {[["2-8" "3-7"]] '[(3 4 5 6 7) (2 3 4 5 6 7 8)]}
  [shifts] 
  (->> shifts
    	(map shift-to-range)
    	(sort-by count)))

(defpure shifts-overlap?
  {[["2-8" "3-7"]] true
   [["2-4" "6-8"]] false
   [["6-6" "4-6"]] true
   [["4-6" "6-6"]] true}
  [shifts]
  (let [sorted       (sort-shifts shifts)
        first-shift  (first sorted)
        second-shift (second sorted)]
    (set/subset? (set first-shift) (set second-shift))))

(defpure count-overlaps
  {[input] 2}
  [^String shifts]
  (->> (parse-input shifts)
       (filter shifts-overlap?)
       (count)))

(comment 
 (run-tests))