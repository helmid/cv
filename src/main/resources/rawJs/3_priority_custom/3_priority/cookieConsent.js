function initializeConsent(content) {
    // https://raw.githubusercontent.com/osano/cookieconsent/dev/LICENSE
    window.cookieconsent.initialise({
        revokeBtn: "<div class='cc-revoke hg-visibility-gone' id='revoke-consent-decision-button'></div>",
        type: "opt-in",
        position: "bottom-right",
        theme: "edgeless",
        palette: {
            popup: {
                background: "#eee",
                text: "#889"
            },
            button: {
                background: "#315399",
                text: "#FFF"
            }
        },
        content: content,
        onInitialise: function (status) {
            consentDecisionMade(status);
        },
        onStatusChange: function (status) {
            consentDecisionMade(status);
        }
    })
}

function consentDecisionMade(status) {
    switch (status) {
        case cookieconsent.status.allow:
            break;
        case cookieconsent.status.deny:
            break;
        case cookieconsent.status.dismiss:
            break;
    }
}

function revokeConsent() {
    document.getElementById('revoke-consent-decision-button').click()
    consentDecisionMade(cookieconsent.status.deny)
}