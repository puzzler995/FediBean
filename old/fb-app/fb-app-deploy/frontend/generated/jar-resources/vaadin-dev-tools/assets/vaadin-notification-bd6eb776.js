import {a, c as t} from "../vaadin-dev-tools.js";
import "construct-style-sheets-polyfill";
import "lit";
import "lit/decorators.js";
import "lit/directives/class-map.js";
import "lit/static-html.js";

const s = {
    tagName: "vaadin-notification",
    displayName: "Notification",
    elements: [{
        selector: "vaadin-notification-card::part(overlay)",
        displayName: "Notification card",
        properties: a
    }, {selector: "vaadin-notification-card::part(content)", displayName: "Content", properties: t}]
};
export {s as default};
