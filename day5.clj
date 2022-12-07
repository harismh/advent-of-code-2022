(ns com.harismh.advent-of-code-2022.day5
  (:require [com.harismh.advent-of-code-2022.test :refer [defpure run-tests]]))

(def stacks
  '[(\N \Z) (\D \C \M) (\P)])

(def moves
  [{:times 1, :from 1 :to 0} ; zero-adjusted
   {:times 3, :from 0 :to 2}
   {:times 2, :from 1 :to 0}
   {:times 1, :from 0 :to 1}])

(defpure perform-move
  {['[(\N) ()] {:times 1 :from 0 :to 1}]     '[() (\N)]
   [stacks     (first moves)]                '[(\D \N \Z) (\C \M) (\P)]}
  [stacks {:keys [times from to]}]
  (loop [from-stack (stacks from)
         to-stack   (stacks to)
         n          times]
    (if (pos? n)
      (recur 
        (pop from-stack)
        (conj to-stack (peek from-stack))
        (dec n))
      (assoc stacks
        from from-stack
        to to-stack))))

(defpure perform-moves 
  {[stacks moves] '[(\C) (\M) (\Z \N \D \P)]}
  [stacks moves]
  (reduce perform-move stacks moves))

(defpure top-of-stack
  {[stacks moves] "CMZ"}
  [stacks moves]
  (->> (perform-moves stacks moves)
       (map peek)
       (apply str)))

(comment 
 (run-tests))