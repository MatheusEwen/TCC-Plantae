package etec.com.ewen.matheus.appplantae;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import config.ConfiguracaoFirebase;
import helper.Base64Custom;
import model.Planta;

public class TelaAlteracao extends AppCompatActivity {

    EditText editNomePlanta, editFA,editEspecie,editIf;
    Button btnAlterar, btnAlterarFoto;
    RadioGroup rdgLuzA, rdgDiasA, rdgCopA;
    RadioButton rdbAltoA, rdbModeradoA, rdbBaixoA, rdbA, rdb8A, rdb9A, rdb10A, rdb11A, rdb12A, rdb13A,rdbSimA, rdbNaoA;
    ImageView imgFotoA;

    Bitmap imagem;

    private final int PERMISSAO_REQUEST = 2;
    private final int CAMERA = 3;
    private Planta planta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alteracao);
        editNomePlanta = findViewById(R.id.editNomePlanta);
        btnAlterar = findViewById(R.id.btnAlterar);
        rdgLuzA = findViewById(R.id.rdgLuzA);
        rdgDiasA = findViewById(R.id.rdgDiasA);
        rdbAltoA = findViewById(R.id.rdbAltoA);
        rdbBaixoA = findViewById(R.id.rdbBaixoA);
        rdbModeradoA = findViewById(R.id.rdbModeradoA);
        rdbA = findViewById(R.id.rdbA);
        rdb8A = findViewById(R.id.rdb8A);
        rdb9A = findViewById(R.id.rdb9A);
        rdb10A = findViewById(R.id.rdb10A);
        rdb11A = findViewById(R.id.rdb11A);
        rdb12A = findViewById(R.id.rdb12A);
        rdb13A = findViewById(R.id.rdb13A);
        rdgCopA = findViewById(R.id.rdgCopA);
        rdbSimA = findViewById(R.id.rdbSimA);
        rdbNaoA = findViewById(R.id.rdbNaoA);
        editFA = findViewById(R.id.editFA);
        editEspecie = findViewById(R.id.editEspecie);
        editIf = findViewById(R.id.editIf);
        imgFotoA = findViewById(R.id.imgFotoA);
        btnAlterarFoto = findViewById(R.id.btnAlterarFoto);


        //Bundle valores = itDadosRecebidos.getExtras();
        planta = (Planta) getIntent().getSerializableExtra("planta");
        editNomePlanta.setText(planta.getNomePlanta());
        editEspecie.setText(planta.getEspecie());
        switch (planta.getLuz()){
            case "Mediano":
                rdbModeradoA.setChecked(true);
                break;
            case "Alto":
                rdbAltoA.setChecked(true);
                break;
            case "Baixo":
                rdbBaixoA.setChecked(true);
                break;
        }
        switch (planta.getDias()){
            case "1":
            rdbA.setChecked(true);
            break;
            case "2":
            rdb8A.setChecked(true);
                break;
            case "3":
            rdb9A.setChecked(true);
                break;
            case "4":
            rdb10A.setChecked(true);
                break;
            case "5":
            rdb11A.setChecked(true);
                break;
            case "6":
            rdb12A.setChecked(true);
                break;
            case "7":
            rdb13A.setChecked(true);
                break;
        }
        switch (planta.getCop()){
            case"sim":
                rdbSimA.setChecked(true);
                break;
            case "não":
                rdbNaoA.setChecked(true);
                break;
            default:
                break;
        }
        editFA.setText(planta.getAf());
        editIf.setText(planta.getInf());
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imagens = storageReference.child("Imagens");
        String nomeImagem = planta.getChave();
        //Associar o nome com a extensão
        StorageReference imageRef = imagens.child(nomeImagem + ".png");
        final long MEGABYTE = 1024 * 1024;
        imageRef.getBytes(MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgFotoA.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TelaAlteracao.this, "erro: " + e, Toast.LENGTH_SHORT).show();
            }
        });
        btnAlterarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itFotoAlterar = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(itFotoAlterar, CAMERA);
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAlteracoes();
                finish();
            }
        });


    }

    private void salvarAlteracoes() {
        deletarFotoFirebase();
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference firebaseRef = firebase.child("planta")
                .child(idUsuario)
                .child(planta.getChave());
        String nome = editNomePlanta.getText().toString();
        String especie = editEspecie.getText().toString();
        String valorLuz;
        switch (rdgLuzA.getCheckedRadioButtonId()){

            case R.id.rdbAltoA:
                valorLuz = "Alto";
                break;
            case R.id.rdbModeradoA:
                valorLuz = "Mediano";
                break;
            case R.id.rdbBaixoA:
                valorLuz = "Baixo";
                break;
            default:
                valorLuz = "Mediano";
                break;
        }
        String luz = valorLuz;
        String inf = editIf.getText().toString();
        String valorDia;
        switch (rdgDiasA.getCheckedRadioButtonId()){
            case R.id.rdbA:
                valorDia = "1";
                break;
            case R.id.rdb8A:
                valorDia = "2";
                break;
            case R.id.rdb9A:
                valorDia = "3";
                break;
            case R.id.rdb10A:
                valorDia = "4";
                break;
            case R.id.rdb11A:
                valorDia = "5";
                break;
            case R.id.rdb12A:
                valorDia = "6";
                break;
            case R.id.rdb13A:
                valorDia = "7";
                break;
            default:
                valorDia = "2";
                break;
        }
        String dia = valorDia;
        String valorCop;
        switch (rdgCopA.getCheckedRadioButtonId()){
            case R.id.rdbSimA:
                valorCop = "sim";
                break;
            case R.id.rdbNaoA:
                valorCop = "Não";
                break;
            default:
                valorCop = "Tanto faz";
                break;
        }
        String cop = valorCop;
        String Af = editFA.getText().toString();
        firebaseRef.child("af").setValue(Af);
        firebaseRef.child("cop").setValue(cop);
        firebaseRef.child("dias").setValue(dia);
        firebaseRef.child("inf").setValue(inf);
        firebaseRef.child("luz").setValue(luz);
        firebaseRef.child("nomePlanta").setValue(nome);
        firebaseRef.child("especie").setValue(especie);

        //configura imagem para ser salva em memoria
        //imgFotoA.setDrawingCacheEnabled(true);
        //imgFotoA.buildDrawingCache();

        //recupera o bitmap da imagem a ser carregada
        Bitmap bitmap = ((BitmapDrawable) imgFotoA.getDrawable()).getBitmap();
        //comprimir a imagem em formato
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);
        //converter o baos em pixels para enviar
        byte[] dadosIamgem = baos.toByteArray();

        //Firebase
        //acessar o firebase
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        //criar a pasta firebase (child)
        StorageReference imagens = storageReference.child("Imagens");
        //gerar um nome aleatorio para a imagem
        String nomeImagem = planta.getChave();
        //Associar o nome com a extensão
        StorageReference imageRef = imagens.child(nomeImagem + ".jpeg");

        UploadTask uploadTask = imageRef.putBytes(dadosIamgem);
        uploadTask.addOnFailureListener(TelaAlteracao.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TelaAlteracao.this, "Erro do Upload" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(TelaAlteracao.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(TelaAlteracao.this, "Upload Feito", Toast.LENGTH_SHORT).show();

            }
        });

        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()){
                    throw task.getException();
                }
                return imageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> task) {
                Uri downloadUri = null;
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                    String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
                    DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
                    firebase.child("planta")
                            .child(idUsuario)
                            .child(planta.getChave())
                            .child("url")
                            .setValue(downloadUri.toString());
                    Toast.makeText(TelaAlteracao.this, "" + downloadUri.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void deletarFotoFirebase() {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imgRef;
        imgRef = storageReference.child("Imagens").child(planta.getChave()+".jpeg");
        imgRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(TelaAlteracao.this , "Planta apagada com sucesso", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(TelaAlteracao.this , "erro: "+ e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA) {
            //O QUE FAZER COM A FOTO TIRADA NA CÂMERA
            imagem = (Bitmap) data.getExtras().get("data");
            imgFotoA.setImageBitmap(imagem);
        }
    }
}