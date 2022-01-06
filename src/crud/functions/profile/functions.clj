(ns crud.functions.profile.functions
  (:use
    [crud.database.profile.queries],
    [crud.helpers.helpers]),
  (:require
    [clojure.data.json :as json]))

(defn make-profile [request]
    (let [nm (:json-params request)],
       (let [name (nm :name)]
         (make name)
         {:status  201
          :headers headerModified
          :body    (make-json {:msg "Cadastrado com Sucesso!"})})))

(defn read-profiles [request]
    (let [response (readAll request)]
       {:status  200
        :headers headerModified
        :body    (json/write-str response )}))

(defn read-profile-by-id [request]
  (let [response  (readById (get-in request [:path-params :id]))]
      {:status   200
        :headers headerModified
           :body (json/write-str response )}))

