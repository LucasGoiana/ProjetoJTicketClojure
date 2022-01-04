(ns crud.functions.status.functions
  (:use
    [crud.database.status.queries],
    [crud.helpers.helpers]),
  (:require
    [clojure.data.json :as json]))

(defn make-status [request]
  (let [nm (:json-params request)],
    (let [name-status (nm :name)]
      (make name-status)
      {:status 201
       :headers header-modified
       :body (make-json {:msg "Cadastrado com Sucesso!"})})))

(defn read-status [request]
  (let [response (readAll request)]
    {:status 200
     :headers header-modified
     :body (json/write-str response )}
    ))

(defn read-status-by-id [request]
  (let [response  (readById (get-in request [:path-params :id]))]
    {:status 200
     :headers header-modified
     :body (json/write-str response )}))
