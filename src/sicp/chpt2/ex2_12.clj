(ns sicp.chpt2.ex2-12)


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


(defn make-center-percent
  [c p]
  (let [width (Math/abs (* (/ p 100.0) c))]
    (make-interval (- c width)
                   (+ c width))))


(defn center
  [i]
  (/ (+ (lower-bound i)
        (upper-bound i))
     2))


(defn percent
  [i]
  (let [c (center i)]
    (Math/abs (* (/ (- (upper-bound i) c)
                    c)
                 100.0))))


;;     (center (make-center-percent 100 10))
;;     => 100

;;     (percent (make-center-percent 100 10))
;;     => 10.0
