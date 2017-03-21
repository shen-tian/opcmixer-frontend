(ns opcmixer-frontend.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :app-log
 (fn [db _]
   (:app-log db)))

(re-frame/reg-sub
 :color
 (fn [db _]
   (:hue db)))

(re-frame/reg-sub
 :pix
 (fn [db _]
   (:pix db)))
