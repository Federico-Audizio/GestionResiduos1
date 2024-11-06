package com.example.gestinresiduos1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val barrio = intent.getStringExtra("barrio") ?: return

        // Verificar si el permiso de notificación está otorgado
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Crear la notificación
            val notification = NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Recolección de basura")
                .setContentText("El camión de basura pasará por $barrio hoy.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(0, notification)
        }
    }
}

