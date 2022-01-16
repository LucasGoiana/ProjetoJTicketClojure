(ns crud.functions.status.functions
  (:use
    [crud.database.status.queries],
    [crud.helpers.helpers],
    [crud.functions.login.jwt]),
  (:require
    [clojure.data.json :as json]))

(defn make-status [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
        (let [name (nm :name)]
          (make name)
          (return 201  (make-json {:msg "Cadastrado com Sucesso!"}))))
          (return 401  (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn read-status [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response (readAll )]
        (return 200 (json/write-str response)))
        (return 401  (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn read-status-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response  (readById (get-in request [:path-params :id]))]
        (return 200 (json/write-str response)))
        (return 401  (make-json {:msg "Login incorreto, por favor tente novamente!"})))))
