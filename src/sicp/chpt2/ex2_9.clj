(ns sicp.chpt2.ex2-9)


(defrecord Interval [lb ub])


(defn make-interval
  [lb ub]
  (Interval. lb ub))


(defn lower-bound
  [i]
  (.lb i))


(defn upper-bound
  [i]
  (.ub i))


(defn add-interval
  [x y]
  (make-interval (+ (lower-bound x)
                    (lower-bound y))
                 (+ (upper-bound x)
                    (upper-bound y))))


(defn sub-interval
  [x y]
  (let [a (- (lower-bound x)
             (lower-bound y))
        b (- (upper-bound x)
             (upper-bound y))]
    (if (< a b)
      (make-interval a b)
      (make-interval b a))))


(defn mul-interval
  [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))


(defn div-interval
  [x y]
  (mul-interval x
                (make-interval (/ (lower-bound y))
                               (/ (upper-bound y)))))


(defn width
  [x]
  (/ (- (upper-bound x)
        (lower-bound x))
     2))

;;; switching to infix for arithmetic

;;; let x, y be two intervals
;;; let lb be lower-bound
;;; let ub be upper-bound
;;; (width x) => [(ub x) - (lb x)]/2
;;; (width y) => [(ub y) - (lb y)]/2

;;; let z be (add-interval x y)
;;; (lb z) => (lb x) + (lb y)
;;; (ub z) => (ub x) + (ub y)

;;; (width z) => [(ub z) - (lb z)]/2
;;;           => [((ub x) + (ub y)) - ((lb x) + (lb y))]/2
;;;           => [(ub x) + (ub y) - (lb x) - (lb y)]/2
;;;           => [(ub x) - (lb x) + (ub y) - (lb y)]/2
;;;           => [(ub x) - (lb x)]/2 + [(ub y) - (lb y)]/2
;;;           => (width x) + (width y)

;;; Therefore (width (add-interval x y)) => (+ (width x) (width y))

;;; Similarly for subtraction


;;; For multiplication/division, the max and min of the products of the bounds are taken,
;;; therefore the width of the multiplication/division of 2 intervals
;;; isn't a  multiplication or division of the widths of the bounds of the intervals
