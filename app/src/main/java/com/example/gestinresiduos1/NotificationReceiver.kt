package com.example.gestinresiduos1

import android.app.PendingIntent
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
            // Crear el intent para abrir la actividad al tocar la notificación
            val resultIntent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Crear la notificación con el PendingIntent
            val notification = NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Recolección de basura")
                .setContentText("El camión de basura pasará por $barrio hoy.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(System.currentTimeMillis().toInt(), notification) // ID único
        }
    }
}


