(ns app-sketch.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(defn draw []
  ; make background white
  (q/background 235)
  (q/stroke-weight 3)
  (q/stroke 255 0 0 120)
  (q/shininess 2)
  (q/emissive 0 0 255)
  (q/fill 130 89 209

  ; move origin point to centre of the sketch
  ; by default origin is in the left top corner
    (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
      ; parameter t goes 0, 0.01, 0.02, ..., 99.99, 100
      (doseq [t (range 4 190 0.71)]
        ; draw a point with x = t * sin(t) and y = t * cos(t)
        (q/point (* t (q/sin t))
                 (* t (q/cos t)))))))
  ; run sketch
(q/defsketch trigonometry
  :size [900 900]
  :draw draw)
