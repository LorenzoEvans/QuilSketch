(ns app-sketch.core
  (:require [quil.core :as q :refer defsketch]
            [quil.middleware :as m]))


(def cos q/cos)
(def sin q/sin)
(def defsketch q/defsketch)
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
        (q/point (* t (sin t))
                 (* t (cos t)))))))
  ; run sketch


(q/defsketch trigonometry
  :size [900 900]
  :draw draw-plot)


(defn fx
  [t]
  (let [r (* 140 (sin t) (cos t))]
    [(* r (sin (* t 0.4)))
     (* r (sin (* t 0.67)))]))

(defn f1
  [t]
  [(* t (q/sin t))
   (* t (q/cos t))])

(defn draw-plot [fx from to step]
  (doseq [pair (->> (range from to step)
                    (map f1)
                    (partition 2 1))]
    (apply q/line pair)))

(defn flower
  []
  (q/background 255)
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
    (draw-plot fx 5 100 0.31)))

(q/defsketch vis
  :size [500 500]
  :draw flower)

(defn f [t]
  (let [r (* 200 (q/sin t) (q/cos t))]
    [(* r (q/sin (* t 0.2)))
     (* r (q/cos (* t 0.2)))]))

(defn f3 [t]
  [(* t (q/sin t))
   (* t (q/cos t))])

(defn draw-plots [f from to step]
  (doseq [two-points (->> (range from to step)
                          (map f3)
                          (partition 2 1))]
    ; we could use 'point' function to draw a point
    ; but let's rather draw a line which connects 2 points of the plot
    (apply q/line two-points)))

(defn draw []
  (q/background 235)
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
   (draw-plots f3 0 140 0.31)))
