(ns app-sketch.random-walk
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def ^:dynamic *y* 0) ; vertical
(def ^:dynamic *x* 0) ; horizontal
; dynamic meta-data and earmuffs to signal these values will change

(def ^:dynamic y-start (/ *y* 2))
; Center of vertical axis
(def ^:dynamic x-start (/ *x* 2))
; Center of horizonal axis.

(q/stroke 255 0 0)
; We can define a stroke from within Quil

(def point (q/point x-start y-start))
; We can define a point from within Quil.
(def choice (rand-int 4))
; We can use rand int for the random
; steps, and we don't have to cast!

(defn step [choice]
  ; Depending on the random integer in choice, we inc or dec x or y.
  (condp = choice
    0 (inc *x*)
    1 (dec *x*)
    2 (inc *y*)
    (dec *y*)))

(defn draw [])

(defn setup []
  (q/frame-rate 10)
  (q/stroke 255 0 0) ; We can define a stroke from within Quil
  (q/background 255))

(q/defsketch Walker
  :size [640 360]
  :draw draw
  :setup setup
  :update nil) ; we're going to have to update to display the walks
               ; we're going to need to tweak draw/frame-rate/count.
