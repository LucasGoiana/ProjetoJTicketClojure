(ns crud.functions.profile.functions
  (:use
    [crud.database.profile.queries],
    [crud.helpers.helpers],
    [crud.functions.login.jwt]),
  (:require
    [clojure.data.json :as json]))

(defn make-profile [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
        (let [name (nm :name)]
          (make name)
          (return 201  (make-json {:msg "Cadastrado com Sucesso!"}))))
          (return 401  (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn read-profile-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response  (readById (get-in request [:path-params :id]))]
        (return 200 (json/write-str response)))
        (return 401  (make-json {:msg "Login incorreto, por favor tente novamente!"})))))

(defn read-profiles [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response (readAll)]
        (return 200 (json/write-str response)))
        (return 401  (make-json {:msg "Login incorreto, por favor tente novamente!"})))))




