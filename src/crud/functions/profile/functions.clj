(ns crud.functions.profile.functions
  (:use
    [crud.database.profile.queries],
    [crud.helpers.helpers]),
  (:require
    [clojure.data.json :as json]))

(defn make-profile [request]
    (let [nm (:json-params request)],
       (let [nome-perfil (nm :nome)]
         (inserir nome-perfil)
         {:status 201
          :headers header-modified
          :body (make-json {:msg "Cadastrado com Sucesso!"}) })))

(defn read-profiles [request]
    (let [response (ler request)]
       {:status 200
        :headers header-modified
        :body (json/write-str response )}))

(defn read-profile-by-id [request]
  (println header-modified)
  (let [response  (lerPorId (get-in request [:path-params :id]))]
      {:status 200
        :headers header-modified
           :body (json/write-str response )}))

