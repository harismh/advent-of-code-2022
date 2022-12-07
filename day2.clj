(ns com.harismh.advent-of-code-2022.day2
  (:require [com.harismh.advent-of-code-2022.test :refer [defpure run-tests]]
    		    [clojure.string :as s]))

(def ^:const rock 1)
(def ^:const paper 2)
(def ^:const scissors 3)

(def ^:const lose 0)
(def ^:const draw 3)
(def ^:const win 6)

; A/B/C opponent choice
; X/Y/Z player choice
; A or X = rock
; B or Y = paper
; C or Z = scissors
(def outcomes
  {"A X" (+ rock draw)
   "A Y" (+ paper win)
   "A Z" (+ scissors lose)
   "B X" (+ rock lose)
   "B Y" (+ paper draw)
   "B Z" (+ scissors win)
   "C X" (+ rock win)
   "C Y" (+ paper lose)
   "C Z" (+ scissors draw)})

(def input "A Y
B X
C Z")

(defpure parse-input 
  {[input] ["A Y" "B X" "C Z"]}
  [^String input] 
  (s/split input #"\n"))

(defpure score
  {[input] 15}
  [^String input]
  (->> 
    (parse-input input)
    (transduce (map outcomes) + 0)))

(comment 
 (run-tests))
