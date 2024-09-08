class TextEditorOpener {
    companion object {
        fun openInEditor(filename:String){
            if (System.getProperty("os.name").startsWith("Windows")) {
                val p = Runtime.getRuntime().exec("notepad $filename.ppm")
            }else{
                val p = Runtime.getRuntime().exec("gedit $filename.ppm")
            }
        }
    }
}