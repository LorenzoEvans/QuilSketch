(ns app-sketch.random-walk
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def y 0) ; vertical
(def x 0) ; horizontal

(def y-start (/ y 2))
; Center of vertical axis
(def x-start (/ x 2))
; Center of horizonal axis.

(q/stroke 255 0 0)

(q/point x-start y-start)

(q/defsketch Walker)
