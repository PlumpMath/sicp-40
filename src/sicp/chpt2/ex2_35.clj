(ns sicp.chpt2.ex2-35)


(defn accumulate
  [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))


(declare count-leaves)


(defn count-coll
  [x]
  (if (coll? x)
    (count-leaves x)
    1))


(defn count-leaves
  [t]
  (accumulate + 0 (map count-coll t)))


;;      (count-leaves (list (list (list 1 2) (list 3 4))
;;                          (list (list 5 6) (list 7 8))))
;;      => 8
