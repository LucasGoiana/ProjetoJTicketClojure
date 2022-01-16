(ns crud.helpers.helpers
  (:require
    [clojure.data.json :as json]
    [clj-time.format :as f]
    ))

(defn make-json [m]
  (json/write-str m ))

(def headerModified {"Access-Control-Allow-Origin" "*"  "Access-Control-Allow-Headers" "'Access-Control-Allow-Headers: Origin, X-Auth-Token'" "Content-Type" "application/json"})


(defn make-slug [name id]
   (clojure.string/lower-case (str  (clojure.string/replace name  #" " "-")"-"id)))

(defn return [status, return]
  {:status  status
   :headers headerModified
   :body     return})