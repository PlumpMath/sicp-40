(ns sicp.chpt1.ex1-24
  (:use [sicp.chpt1.ex1-03 :only [sqr]])
  (:require [sicp.chpt1.ex1-22 :as c1e22]))


(defn expmod
  [base exp m]
  (cond (zero? exp)
        1

        (even? exp)
        (rem (sqr (expmod base (/ exp 2) m)) m)

        :else
        (rem (* base (expmod base (dec exp) m)) m)))


(defn fermat-test
  [n]
  (letfn [(try-it [a]
            (= (expmod a n n) a))]
    (try-it (+ 1 (int (rand n))))))


(defn prime?
  ([n]
     (prime? n (int (Math/log n))))
  ([n times]
     (if (zero? times)
       true
       (and (fermat-test n) (prime? n (dec times))))))


(def timed-prime-test
  #(c1e22/timed-prime-test % :prime-fn prime?))


(def find-timed-primes-between
  #(c1e22/find-timed-primes-between %1 %2 :timed-prime-test-fn timed-prime-test))


;; Each test was run 100,000 times before the time was recorded to account for HotSpot optimization

;; `(take 3 (find-timed-primes-between 1000 1000000000))`

;; * 1009, 0.043298 msec
;; * 1013, 0.044814 msec
;; * 1019, 0.042874 msec

;; average time taken, 0.04366 msec

;; `(take 3 (find-timed-primes-between 10000 1000000000))`

;; * 10007, 0.062862 msec
;; * 10009, 0.064997 msec
;; * 10037, 0.06907 msec

;; average time taken, 0.065654 msec

;; `(take 3 (find-timed-primes-between 100000 1000000000))`

;; * 100003, 0.084971 msec
;; * 100019, 0.083323 msec
;; * 100043, 0.083917 msec

;; average time taken, 0.08407 msec

;; `(take 3 (find-timed-primes-between 1000000 1000000000))`

;; * 1000003, 0.116445 msec
;; * 1000033, 0.119691 msec
;; * 1000037, 0.108891 msec

;; average time taken, 0.11501 msec

;; Since the growth is \\(\log _2 n\\), I would have expected the time to test primes near \\(10^6\\) was
;; roughly \\(\log _2 1000\\) times greater that testing primes near \\(10^3\\), roughly 10 times larger

;; It was closer to 2.5 times larger

;; This discrepancy is probably due to the stochastic nature of this algorithm, which can terminate earlier
;; if the primality test fails
