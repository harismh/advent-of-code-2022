(ns com.harismh.advent-of-code-2022.day3
  (:require [com.harismh.advent-of-code-2022.test :refer [defpure run-tests]]
            [clojure.string :as s]
            [clojure.set :as set]))

(def input "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(defpure split-lines 
  {[input] ["vJrwpWtwJgWrhcsFMMfFFhFp" "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL" "PmmdzqPrVvPwwTWBwg" "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn" "ttgJtRGJQctTZtZT" "CrZsJsPPZsGzwwsLwLmpwMDw"]}
  [^String s]
  (s/split s #"\n *"))

(defpure split-in-half
  {["vJrwpWtwJgWrhcsFMMfFFhFp"] ["vJrwpWtwJgWr" "hcsFMMfFFhFp"]}
  [^String s]
  (let [length (count s)
        middle (quot length 2)
        first-half  (subs s 0 middle)
        second-half (subs s middle length)] 
    [first-half second-half]))

(defpure common-letter
  {[["vJrwpWtwJgWr" "hcsFMMfFFhFp"]] \p}
  [strings]
  (->> (map set strings) 
       (apply set/intersection) 
       (first)))

(def priority 
  (zipmap "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" (range 1 53)))

(defpure prioritize
  {["vJrwpWtwJgWrhcsFMMfFFhFp"] 16}
  [^String rucksack]
  (->>
    (split-in-half rucksack)
    (common-letter)
    (priority)))

(defpure prioritize-rucksacks
  {[input] 157}
  [^String rucksacks]
   (->> (split-lines rucksacks)
        (transduce (map prioritize) + 0)))

(comment 
 (run-tests))