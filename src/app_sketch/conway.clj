(ns app-sketch.conway
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))


;;;
;;; Game of Life
;;; https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
;;;8

(def grid 20)

(def state {:matrix (vec
                     (repeatedly (* grid grid) #(rand-int 3)))})

(defn interval []
  (let [vec
        (vec (repeatedly (* grid (/ grid 2))  #(rand-int 255)))]
    vec))

(defn interstate [s-vec num-vec]
  (interleave num-vec s-vec))

(defn setup []
  (q/frame-rate 10)
  (q/color-mode :hsb)
  (q/no-stroke)
  state)

(defn neighbors [idx mtrx]
  [
   (get mtrx (dec (- idx grid)))
   (get mtrx (- idx grid))
   (get mtrx (inc (- idx grid)))

   (get mtrx (dec idx))
   (get mtrx (inc idx))

   (get mtrx (dec (+ grid idx)))
   (get mtrx (+ grid idx))
   (get mtrx (inc (+ grid idx)))])


(defn new-cells [idx val mtrx]
  (let [alive-n (get (frequencies (neighbors idx mtrx)) 1 0)]
    (if (= 1 val)
      (if (or (> alive-n 3) (< alive-n 2)) 0 1)
      (if (= 3 alive-n) 1 0))))



(defn alt-cells [idx val mtrx]
  (let [alive-n (get (frequencies (neighbors idx mtrx)) 1 0)]
    (cond
      (= val 1
         (cond
           (or (> alive-n 3) (< alive-n 2)) 0
           (= alive-n 3) 1
           (or (> alive-n 4) (< alive-n 3)) 0))
      (= val 0
        (cond
          (or (> alive-n 3) (< alive-n 2)) 0
          (= alive-n 3) 1
          (or (> alive-n 4) (< alive-n 3)) 0))
      (= val 2
        (cond
          (or (> alive-n 3) (< alive-n 2)) 0
          (= alive-n 3) 1
          (or (> alive-n 4) (< alive-n 3)) 0))
      (= val 3
        (cond
          (or (> alive-n 3) (< alive-n 2)) 0
          (= alive-n 3) 1
          (or (> alive-n 4) (< alive-n 3)) 0)))))





(defn update-cells [state]
  (assoc state :matrix
    (vec
      (map-indexed
       (fn [idx val] (new-cells idx val (:matrix state)))
       (:matrix state)))))

(defn animate [state]
  (q/background 255)
  (let [cell (quot (q/width) grid)]
    (doseq [[i v] (map-indexed vector (:matrix state))]
      (let [multiplier (int (/ i grid))
            x (* cell (- i (* multiplier grid)))
            y (* cell multiplier)]
        (q/fill
         (if (= 1 v) (rand-nth (interval)) 0)
         (if (= v 2) (rand-nth (interval)) 255))
        (q/rect x y cell cell)))))

(q/defsketch conway
  :size [600 600]
  :setup setup
  :update update-cells
  :draw animate
  :middleware [m/fun-mode])
