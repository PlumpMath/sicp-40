(ns sicp.chpt2.ex2-01
  (:use [sicp.chpt1.ex1-20 :only [gcd]]))


;;; Using Clojure's records instead of cons cells as a means of combination


(defrecord Rational [n d])


(defn make-rat
  [n d]
  (let [div (Math/abs (gcd n d))
        n-div (if (or (and (pos? n) (neg? d))
                      (and (neg? n) (neg? d)))
                    (- div)
                    div)]
    (Rational. (/ n n-div) (/ d n-div))))


;;;     (make-rat 10 20)
;;;     => #sicp.chpt2.ex2_01.Rational{:n 1, :d 2}
