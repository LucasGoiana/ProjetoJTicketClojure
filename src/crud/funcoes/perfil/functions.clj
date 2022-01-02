(ns crud.funcoes.perfil.functions
  (:use
    [crud.database.perfil.queries],
    [crud.helpers.helpers]),
  (:require
    [clojure.data.json :as json]))

(defn criar-perfil [request]
    (let [nm (:json-params request)],
       (let [nome-perfil (nm :nome)]
         (inserir nome-perfil)
         {:status 201
          :headers header-modified
          :body (make-json {:msg "Cadastrado com Sucesso!"}) })))

(defn ler-perfis [request]
    (let [response (ler request)]
       {:status 200
        :headers header-modified
        :body (json/write-str response )}))

(defn ler-perfil [request]
  (println header-modified)
  (let [response  (lerPorId (get-in request [:path-params :id]))]
      {:status 200
        :headers header-modified
           :body (json/write-str response )}))

