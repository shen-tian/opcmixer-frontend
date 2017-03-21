(ns opcmixer-frontend.views
    (:require [re-frame.core :as re-frame]
              [thi.ng.color.core :as col]))

(defn log-panel
  []
  (let [log (re-frame/subscribe [:pix])]
    [:p @log]))

(defn channel-panel
  []
  (fn []
    [:div
     [:p "Channel x"]
     [:input {:type "checkbox"}]
     [:p "Levels"]
     [:input {:type "range"}]
     [:p "Hue"]
     [:input {:type "range"
              :min 0
              :max 255
              :value (* 255@(re-frame/subscribe [:color]))
              :on-change #(re-frame/dispatch 
                          [:set-color 
                           (/ (-> % .-target .-value) 255)])}]
     [:br]
     [:svg {:width 100
            :height 100
            :viewBox "0 0 100 100"}
      [:circle {:cx 50
                :cy 50
                :r 50
                :style {:fill @(->> @(re-frame/subscribe [:pix])
                                    (apply col/rgba)
                                    col/as-css)}}]]]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div 
       [:h1 "Hello from " @name]
       [:button {:type "button"
                 :on-click #(re-frame/dispatch [:get-frame])} "Refresh"]
       [:button {:type "button"
                 :on-click #(re-frame/dispatch [:set-color (rand)])}
        "Color"]
       [channel-panel]
       [log-panel]])))
