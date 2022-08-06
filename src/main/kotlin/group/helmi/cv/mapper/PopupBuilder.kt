package group.helmi.cv.mapper

import group.helmi.cv.config.localization.Translator

object PopupBuilder {
    fun makeAlert(success: Boolean): String {
        val clazz = if (success) "modal-confirm" else "modal-error"
        val icon = if (success) "&#xE876;" else "&#xE14C;"
        val buttonClazz = if (success) "btn hg-btn-success btn-block" else "btn hg-btn-danger btn-block"
        val message = Translator.toLocale(if (success) "contact_request_success" else "contact_request_error")
        return """
        <div id="form-status" class="modal fade">
            <div class="modal-dialog modal-status $clazz" >
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="icon-box">
                            <i class="material-icons">$icon</i>
                        </div>
                    </div>
                    <div class="modal-body">
                        <p class="text-center">$message</p>
                    </div>
                    <div class="modal-footer">
                        <button class="$buttonClazz" href="#form-status" data-toggle="modal">${Translator.toLocale("general_dialog_ok")}</button>
                    </div>
                </div>
            </div>
        </div>
    """.trimIndent()
    }
}