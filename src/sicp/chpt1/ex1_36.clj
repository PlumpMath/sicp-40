(ns sicp.chpt1.ex1-36
  (:use [sicp.chpt1.ex1-07 :only [abs]]))


(def tolerance 1/100000)


(defn fixed-point
  [f first-guess]
  (letfn [(close-enough? [v1 v2]
            (< (abs (- v1 v2)) tolerance))

          (try-it [guess i]
            (println guess i)
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (recur next (inc i)))))]

    (try-it first-guess 0)))


;;; Solutions to \\(x^x = 1000\\)

;;; \\(x \rightarrow \frac{\log 1000}{\log x}\\)


(defn without-damping
  []
  (fixed-point (fn [x]
                 (/ (Math/log 1000)
                    (Math/log x)))
               10))


;;; without damping takes 32 steps to converge

;;;     (without-damping)
;;;     # 10 0
;;;     # 2.9999999999999996 1
;;;     # 6.2877098228681545 2
;;;     # 3.7570797902002955 3
;;;     # 5.218748919675316 4
;;;     # 4.1807977460633134 5
;;;     # 4.828902657081293 6
;;;     # 4.386936895811029 7
;;;     # 4.671722808746095 8
;;;     # 4.481109436117821 9
;;;     # 4.605567315585735 10
;;;     # 4.522955348093164 11
;;;     # 4.577201597629606 12
;;;     # 4.541325786357399 13
;;;     # 4.564940905198754 14
;;;     # 4.549347961475409 15
;;;     # 4.5596228442307565 16
;;;     # 4.552843114094703 17
;;;     # 4.55731263660315 18
;;;     # 4.554364381825887 19
;;;     # 4.556308401465587 20
;;;     # 4.555026226620339 21
;;;     # 4.55587174038325 22
;;;     # 4.555314115211184 23
;;;     # 4.555681847896976 24
;;;     # 4.555439330395129 25
;;;     # 4.555599264136406 26
;;;     # 4.555493789937456 27
;;;     # 4.555563347820309 28
;;;     # 4.555517475527901 29
;;;     # 4.555547727376273 30
;;;     # 4.555527776815261 31
;;;     # 4.555540933824255 32
;;;     => 4.555532257016376


;;; with damping

;;; \\(2x \rightarrow x + \frac{\log 1000}{\log x}\\)

;;; \\(\Rightarrow x \rightarrow \frac{x\log x + \log 1000}{2\log x}\\)


(defn with-damping
  []
  (fixed-point (fn [x]
                 (let [logx (Math/log x)]
                   (/ (+ (* x logx) (Math/log 1000))
                      (* 2 logx))))
               10))


;;; with damping takes 9 steps to converge

;;;     (with-damping)
;;;     # 10 0
;;;     # 6.5 1
;;;     # 5.095215099176933 2
;;;     # 4.668760681281611 3
;;;     # 4.57585730576714 4
;;;     # 4.559030116711325 5
;;;     # 4.55613168520593 6
;;;     # 4.555637206157649 7
;;;     # 4.5555529875456395 8
;;;     # 4.555538647701617 9
;;;     => 4.555536206185039
