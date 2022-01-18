(ns crud.functions.email.sendEmail
  (:use [config.core :refer [env]])
  (:require
    [postal.core :as postal]
    ))

(defn sendEmail[idTicket, nameTicket, status]
  (postal/send-message
    {:host (:host-email  env)
     :port (:port-email  env)
     :tls true
     :user (:user-email env)
     :pass (:pass-email  env)}
    {:from (:user-email env)
     :to (:to-email  env)
     :subject (:subject-email env)
     :body (str  "Olá, \nO Ticket: \"" idTicket "-" nameTicket "\" foi alterado para o status " status)}))
