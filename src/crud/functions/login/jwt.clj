(ns crud.functions.login.jwt
  (:require
    [buddy.sign.jwt :as jwt]))

(defonce secret "86bae26023208e57a5880d5ad644143c567fc57baaf5a942")

(defn generate-signature [idUser idProfile]
   (jwt/sign {:idUser idUser, :idProfile idProfile} secret))

(defn unsign-token [token]
  (jwt/unsign token secret))