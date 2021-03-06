/*
 * Copyright (C) 2013 AChep@xda <artemchep@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package com.achep.activedisplay.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

import com.achep.activedisplay.admin.AdminReceiver;
import com.achep.activedisplay.notifications.NotificationHandleService;

import java.lang.ref.WeakReference;

/**
 * Created by Artem on 23.01.14.
 */
public class AccessUtils {

    private static WeakReference<ComponentName> mAdminComponentName;

    public static boolean isDeviceAdminEnabled(Context context) {
        ComponentName admin;
        if (mAdminComponentName == null || mAdminComponentName.get() == null) {
            admin = new ComponentName(context, AdminReceiver.class);
            mAdminComponentName = new WeakReference<>(admin);
        } else {
            admin = mAdminComponentName.get();
        }

        DevicePolicyManager dpm = (DevicePolicyManager)
                context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        return dpm.isAdminActive(admin);
    }

    public static boolean isNotificationAccessEnabled(Context context) {
        return NotificationHandleService.isServiceRunning(context);
    }

}
