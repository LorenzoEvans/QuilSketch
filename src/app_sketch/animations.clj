(ns app-sketch.animations
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn f [t]
  (let [r (* 200 (q/sin t) (q/cos t))]
    [(* r (q/sin (* t 0.5)))
     (* r (q/cos (* t 0.5)))]))

(defn drawer []
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
    ; note that we don't use draw-plot here as we need
    ; to draw only small part of a plot on each iteration
    (let [t (/ (q/frame-count) 10)]
      (q/line (f t)
              (f (+ t 0.1)))
      (q/line (f t)
              (f (+ t 3.71))))))

; 'setup' is a cousin of 'draw' function
; setup initialises sketch and it is called only once
; before draw called for the first time
(defn setup []
  ; draw will be called 60 times per second
  (q/frame-rate 60)
  (q/height 190)
  (q/width)
  (q/stroke 89 56 33)
  ; set background to white colour only in the setup
  ; otherwise each invocation of 'draw' would clear sketch completely
  (q/background 255))

(q/defsketch trigonometry
  :size [900 900]
  :setup setup
  :draw drawer)
