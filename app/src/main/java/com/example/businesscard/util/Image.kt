import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.businesscard.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class Image{
    companion object{
        fun share(context: Context, card: View){
            val bitmap = getScreenShotFromView(card)
            bitmap?.let {
                saveMediaToStorege(context,bitmap)
            }
        }


        private fun getScreenShotFromView(card: View): Bitmap? {

            var screenShort: Bitmap? = null
            try {
                screenShort = Bitmap.createBitmap(
                    card.measuredWidth,
                    card.measuredHeight,
                    Bitmap.Config.ARGB_8888
                )

                val canvas = Canvas(screenShort)
                card.draw(canvas)

            } catch (e: Exception) {
                Log.d("Erro", "$e")
            }

            return screenShort
        }

        private fun saveMediaToStorege(context: Context, bitmap: Bitmap) {
            val filename = "${System.currentTimeMillis()}.jpg"

            var fos: OutputStream?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                context.contentResolver?.also { resolver->

                    val contenteValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME,filename)
                        put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES)
                    }

                    val imageUri: Uri? =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contenteValues)

                    fos = imageUri?.let {
                        shareItent(context,imageUri)
                        resolver.openOutputStream(it)
                    }

                }

            }else{
                //Devices rodando < Q
                val imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imageDir, filename)
                shareItent(context, Uri.fromFile(image))
                fos = FileOutputStream(image)
                fos?.use{
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,it)
                    Toast.makeText(context, "Image salvada com sucesso ", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun shareItent(context: Context, uri: Uri) {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM,uri)
                type = "image/jpg"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.resources.getText(R.string.label_share)
                )
            )
        }

    }

}


