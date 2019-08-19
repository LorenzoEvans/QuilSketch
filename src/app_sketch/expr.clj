(ns app-sketch.expr
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn f [t]
  (let [r (* 200 (q/sin t) (q/cos t))]
    [(* r (q/sin (* t 0.5)))
     (* r (q/cos (* t 1.2)))]))

(defn g [t]
  (let [r (* 50 (q/sin t) (q/cos t))]
    [(* r (q/sin (+ t 0.5)))
     (* r (q/cos (+ t 0.2)))]))

(defn drawer []
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
    ; note that we don't use draw-plot here as we need
    ; to draw only small part of a plot on each iteration
    (let [t (/ (q/frame-count) 10)]
      (q/line (f t)
              (f (+ t 0.1)))
      (q/line (f t)
              (f (+ t (* 2 -0.5)))))))

; 'setup' is a cousin of 'draw' function
; setup initialises sketch and it is called only once
; before draw called for the first time
(defn setup []
  ; draw will be called 60 times per second
  (q/frame-rate 1290)
  (q/height 190)
  (q/width)
  (q/stroke 89 56 33)
  ; set background to white colour only in the setup
  ; otherwise each invocation of 'draw' would clear sketch completely
  (q/background 255))



(defn flower [t]
  (let [r (* 500 (q/sin t) (q/cos t))]
    [(* r (q/sin (* t 5.2)))
     (* r (q/cos (* t 2)))]))


(defn alterant [l]
  (let [r (* 500 (q/cos l) (q/sin l))]
    [(* r (q/tan (* l 1.2)))
     (* r (q/tan (* l 2.0)))]))

(defn draw-animation []
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
    (let [t (/ (q/frame-count) 10)]
      ; set color for line
      (q/stroke (mod t 10) 103 134 200)
      (q/stroke (mod t 15) 30 66 90)
      (q/line (flower t) (alterant (* t 1.5))))))



(q/defsketch trigonometry
  :size [900 900]
  :setup setup
  :draw draw-animation)
