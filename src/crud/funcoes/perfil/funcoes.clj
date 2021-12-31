(ns crud.funcoes.perfil.funcoes
  (:use
    [crud.database.perfil.querys]),
  (:require
    [clojure.data.json :as json]))

(defn criar-perfil [request]
           (let [nm (:json-params request)],
             (let [nome-perfil (nm :nome)]
               (inserir nome-perfil))))

(defn ler-perfil [request]
    (let [response (ler request)]
      (println (type response))
      ))
