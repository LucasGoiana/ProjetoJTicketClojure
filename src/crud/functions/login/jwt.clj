(ns crud.functions.login.jwt
  (:require
    [buddy.sign.jwt :as jwt]))

(defonce secret "86bae26023208e57a5880d5ad644143c567fc57baaf5a942")

(defn generate-signature [idUser idProfile]
   (jwt/sign {:idUser idUser, :idProfile idProfile} secret))

(defn validate-jwt [data]
  (let [idUser (get-in data [:idUser])]
     (if (= (nil? idUser) false) true false)))

(defn unsign-token [token]
  (validate-jwt (jwt/unsign token secret)))

