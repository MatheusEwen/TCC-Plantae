package etec.com.ewen.matheus.appplantae.ui.cadastrar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
import etec.com.ewen.matheus.appplantae.R;
import helper.Base64Custom;
import model.Planta;

import static android.app.Activity.RESULT_OK;


public class CadastrarPlanta extends Fragment {

    private EditText edtNomePlanta, edtEspecie, edtInf, edtAduboEFertilizante;
    private RadioButton rdbAlto, rdbModerado,rdbBaixo,rdb1,rdb2,rdb3,rdb4,rdb5,rdb6,rdb7,rdbSim,rdbNao;
    private RadioGroup rdgLuz, rdgDias, rdgCop;
    private Button btnFoto, btnCadastrar;
    private ImageView imgFoto;

    Bitmap imagem;

    private final int PERMISSAO_REQUEST = 2;
    private final int CAMERA = 3;

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_cadastrar_planta, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        edtNomePlanta = (EditText) context.findViewById(R.id.edtNomePlanta);
        imgFoto = (ImageView) context.findViewById(R.id.imgFoto);
        edtEspecie = (EditText) context.findViewById(R.id.edtEspecie);
        edtInf = (EditText) context.findViewById(R.id.edtInf);
        rdbAlto = (RadioButton) context.findViewById(R.id.rdbAlto);
        rdbModerado = (RadioButton) context.findViewById(R.id.rdbModerado);
        rdbBaixo = (RadioButton) context.findViewById(R.id.rdbBaixo);
        rdb1 = (RadioButton) context.findViewById(R.id.rdb1);
        rdb2 = (RadioButton) context.findViewById(R.id.rdb2);
        rdb3 = (RadioButton) context.findViewById(R.id.rdb3);
        rdb4 = (RadioButton) context.findViewById(R.id.rdb4);
        rdb5 = (RadioButton) context.findViewById(R.id.rdb5);
        rdb6 = (RadioButton) context.findViewById(R.id.rdb6);
        rdb7 = (RadioButton) context.findViewById(R.id.rdb7);
        rdbSim = (RadioButton) context.findViewById(R.id.rdbSim);
        rdbNao =(RadioButton) context.findViewById(R.id.rdbNao);
        edtAduboEFertilizante = (EditText) context.findViewById(R.id.edtAduboEFertilizante);
        rdgLuz = (RadioGroup) context.findViewById(R.id.rdgLuz);
        rdgDias = (RadioGroup) context.findViewById(R.id.rdgDias);
        rdgCop = (RadioGroup) context.findViewById(R.id.rdgCop);
        btnFoto = (Button) context.findViewById(R.id.btnFoto);
        btnCadastrar = (Button) context.findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imagem == null){
                    Toast.makeText(context, "Tire uma foto", Toast.LENGTH_SHORT).show();
                }else {
                    salvarPlanta();
                }

            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(itCamera, CAMERA);
            }
        });

        //verificar Permisão
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        ){
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    || (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA))){
            } else {
                ActivityCompat.requestPermissions(context, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, PERMISSAO_REQUEST);
            }
        }
    }
    public void salvarPlanta(){
        Planta planta = new Planta();
        planta.gerarPushKey();
        String nome = edtNomePlanta.getText().toString();
        String especie = edtEspecie.getText().toString();
        String valorLuz;
        switch (rdgLuz.getCheckedRadioButtonId()){

            case R.id.rdbAlto:
                valorLuz = "Alto";
                break;
            case R.id.rdbModerado:
                valorLuz = "Mediano";
                break;
            case R.id.rdbBaixo:
                valorLuz = "Baixo";
                break;
            default:
                valorLuz = "Mediano";
                break;
        }
        String luz = valorLuz;
        String inf = edtInf.getText().toString();
        String valorDia;
        switch (rdgDias.getCheckedRadioButtonId()){
            case R.id.rdb1:
                valorDia = "1";
                break;
            case R.id.rdb2:
                valorDia = "2";
                break;
            case R.id.rdb3:
                valorDia = "3";
                break;
            case R.id.rdb4:
                valorDia = "4";
                break;
            case R.id.rdb5:
                valorDia = "5";
                break;
            case R.id.rdb6:
                valorDia = "6";
                break;
            case R.id.rdb7:
                valorDia = "7";
                break;
            default:
                valorDia = "2";
                break;
        }
        String dia = valorDia;
        String valorCop;
        switch (rdgCop.getCheckedRadioButtonId()){
            case R.id.rdbSim:
                valorCop = "sim";
                break;
            case R.id.rdbNao:
                valorCop = "Não";
                break;
            default:
                valorCop = "Tanto faz";
                break;
        }
        String cop = valorCop;
        String Af = edtAduboEFertilizante.getText().toString();
        planta.setNomePlanta(nome);
        planta.setLuz(luz);
        planta.setEspecie(especie);
        planta.setAf(Af);
        planta.setCop(cop);
        planta.setDias(dia);
        planta.setInf(inf);

        //configura imagem para ser salva em memoria
        imgFoto.setDrawingCacheEnabled(true);
        imgFoto.buildDrawingCache();

        //recupera o bitmap da imagem a ser carregada
        Bitmap bitmap = ((BitmapDrawable) imgFoto.getDrawable()).getBitmap();
        //comprimir a imagem em formato
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 75, baos);
        //converter o baos em pixels para enviar
        byte[] dadosIamgem = baos.toByteArray();

        //Firebase
        //acessar o firebase
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference imagens = storageReference.child("Imagens");

        String nomeImagem = planta.getChave();
        //Associar o nome com a extensão
        StorageReference imageRef = imagens.child(nomeImagem + ".png");

        UploadTask uploadTask = imageRef.putBytes(dadosIamgem);
        uploadTask.addOnFailureListener(context, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Erro do Upload: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(context, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(context, "Upload Feito", Toast.LENGTH_SHORT).show();

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
                    //Toast.makeText(context, "" + downloadUri.toString(), Toast.LENGTH_SHORT).show();
                    //mDatabase = FirebaseDatabase.getInstance().getReference();
                    //mDatabase.child("planta").child(downloadUri.toString());
                }
            }
        });
        planta.salvar();
        context.finish();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == CAMERA) {
            //O QUE FAZER COM A FOTO TIRADA NA CÂMERA
            imagem = (Bitmap) data.getExtras().get("data");
            imgFoto.setImageBitmap(imagem);

        }
    }
}