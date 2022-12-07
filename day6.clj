(ns com.harismh.advent-of-code-2022.day6
  (:require [com.harismh.advent-of-code-2022.test :refer [defpure run-tests]]))

(def input-sample "mjqjpqmgbljsphdztnvjfqwrcgsmlb")
(def input-one "bvwbjplbgvbhsrlpgdmjqwftvncz")
(def input-two "nppdvjthqldpwncqszvftbrmjlhg")
(def input-three "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
(def input-four "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

(defpure find-marker
  {[input-sample]  {:marker '(\j \p \q \m) :start-idx 7}
   [input-one]     {:marker '(\v \w \b \j) :start-idx 5}
   [input-two]     {:marker '(\p \d \v \j) :start-idx 6}
   [input-three]   {:marker '(\r \f \n \t) :start-idx 10}
   [input-four]    {:marker '(\z \q \f \r) :start-idx 11}}
  [^String stream]
  (let [[idx marker] (->> stream 
                          (partition 4 1)
                          (map-indexed vector)
                          (filter #(= 4 (count (set (second %)))))
                          (first))]
    {:marker    marker
     :start-idx (+ idx 4)}))

(defn start-of-marker
  [^String stream]
  (:start-idx (find-marker stream)))

(comment
  (run-tests))