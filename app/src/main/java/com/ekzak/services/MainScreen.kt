package com.ekzak.services

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    context: Context,
) {
    var page = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
//                context.stopService(MyForegroundService.newIntent(context))
                context.startService(MyService.newIntent(context))
            }
        ) {
            Text(text = "Service")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(MyForegroundService.newIntent(context))
                } else {
                    context.startService(MyForegroundService.newIntent(context))
                }
            }
        ) {
            Text(text = "Foreground Service")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.startService(MyIntentService.newIntent(context)) }
        ) {
            Text(text = "Intent Service")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val componentName = ComponentName(context, MyJobService::class.java)

                val jobInfo = JobInfo.Builder(MyJobService.JOB_ID, componentName)
                    .setRequiresCharging(false)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                    .build()

                val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    scheduler.enqueue(jobInfo, JobWorkItem(MyJobService.newIntent(page++)))
                }
            }
        ) {
            Text(text = "Job Scheduler")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Job Intent Service")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Alarm Manager")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Work Manager")
        }
    }
}
