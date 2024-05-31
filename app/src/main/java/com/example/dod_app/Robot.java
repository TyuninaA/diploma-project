package com.example.dod_app;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class Robot extends Fragment {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // UUID для SPP (Serial Port Profile)
    private final String DEVICE_ADDRESS = "98:D3:81:FD:44:82";

    private TextView coordinatesTextView;
    private Button connectButton;
    private Button buttonForward;
    private Button buttonBackward;
    private Button buttonRight;
    private Button buttonLeft;
    //private Button buttonStay;
    private String movementCommand = "115\n"; // Начальное значение "s" в виде числового значения

    // Код для запроса разрешений
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_CODE = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_robot, container, false);

        // Инициализация BluetoothAdapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Проверка поддержки Bluetooth на устройстве
        if (bluetoothAdapter == null) {
            Toast.makeText(getActivity(), "Bluetooth не поддерживается на этом устройстве", Toast.LENGTH_SHORT).show();
            // Можно добавить дальнейшие действия, если Bluetooth не поддерживается
            return view;
        }

        // Проверка на разрешения
        checkPermissions();

        connectButton = view.findViewById(R.id.connect_button);
        //buttonStay = view.findViewById(R.id.buttonStay);
        buttonForward = view.findViewById(R.id.buttonForward);
        buttonBackward = view.findViewById(R.id.buttonBackward);
        buttonLeft = view.findViewById(R.id.buttonLeft);
        buttonRight = view.findViewById(R.id.buttonRight);
        //coordinatesTextView = view.findViewById(R.id.coordinates_textview);

        // Обработчики нажатия на кнопки
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()) {
                    connectToDevice();
                } else {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
            }
        });

//        buttonStay.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    movementCommand = "115\n";
//                    sendMovementCommand();
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    movementCommand = "115\n"; // Отправляем команду для остановки движения
//                    sendMovementCommand();
//                }
//                return true;
//            }
//        });

        buttonForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    movementCommand = "102\n";
                    sendMovementCommand();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    movementCommand = "115\n"; // Отправляем команду для остановки движения
                    sendMovementCommand();
                }
                return true;
            }
        });

        buttonBackward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    movementCommand = "98\n";
                    sendMovementCommand();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    movementCommand = "115\n"; // Отправляем команду для остановки движения
                    sendMovementCommand();
                }
                return true;
            }
        });

        buttonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    movementCommand = "108\n";
                    sendMovementCommand();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    movementCommand = "115\n"; // Отправляем команду для остановки движения
                    sendMovementCommand();
                }
                return true;
            }
        });

        buttonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    movementCommand = "114\n";
                    sendMovementCommand();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    movementCommand = "115\n"; // Отправляем команду для остановки движения
                    sendMovementCommand();
                }
                return true;
            }
        });

        return view;
    }

    private void checkPermissions() {
        // Проверка разрешений
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // Разрешения не предоставлены, запросить их
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH_CONNECT}, PERMISSION_REQUEST_CODE);
        }
    }

    private void connectToDevice() {
        // Проверка разрешений
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            // Разрешения есть, можно продолжить с подключением
            // Поиск устройства с заданным адресом
            bluetoothDevice = bluetoothAdapter.getRemoteDevice(DEVICE_ADDRESS);

            // Попытка установления соединения
            try {
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID);
                bluetoothSocket.connect();
                outputStream = bluetoothSocket.getOutputStream();
                Toast.makeText(getActivity(), "Подключено к устройству", Toast.LENGTH_SHORT).show();
                // Теперь вы можете начать отправку и прием данных через bluetoothSocket
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Ошибка при подключении к устройству", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Если разрешения не предоставлены, запросите их снова или предупредите пользователя о необходимости разрешений
            Toast.makeText(getActivity(), "Необходимо предоставить разрешения на Bluetooth", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMovementCommand() {
        if (outputStream != null) {
            try {
                outputStream.write(movementCommand.getBytes());
                outputStream.flush();
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Ошибка при отправке команды", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == getActivity().RESULT_OK) {
                connectToDevice();
            } else {
                Toast.makeText(getActivity(), "Bluetooth не был включен", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Проверка результатов запроса разрешений
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешения получены, можно выполнить действия, которые требуют эти разрешения
            } else {
                // Одно или несколько разрешений не были предоставлены, выполнить действия при этом сценарии
                Toast.makeText(getActivity(), "Разрешения на Bluetooth не были предоставлены", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
