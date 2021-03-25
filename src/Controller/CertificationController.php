<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


use App\Entity\Certification;
use App\Repository\CertificationRepository;
use App\Form\CertificationType;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;

use Dompdf\Dompdf;
use Dompdf\Options;

class CertificationController extends Controller
{
    /**
     * @Route("/certification", name="certification")
     */
    public function index(): Response
    {
        return $this->render('certification/index.html.twig', [
            'controller_name' => 'CertificationController',
        ]);
    }

 

/**
    * @Route("/pdf", name="pdf")
    */
    public function pdf(Request $request){
	
        



// Configure Dompdf according to your needs
$pdfOptions = new Options();
$pdfOptions->set('defaultFont', 'Arial');

// Instantiate Dompdf with our options
$dompdf = new Dompdf($pdfOptions);
	
$repository=$this->getdoctrine()->getrepository(Certification::class);
$allCertification=$repository->findAll();

// Retrieve the HTML generated in our twig file
$html = $this->renderView('Certification/ListeCertPDF.html.twig', [
    'title' => "Welcome to our PDF Test", 'Certifications'=>$allCertification,
]);

// Load HTML to Dompdf
$dompdf->loadHtml($html);

// (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
$dompdf->setPaper('A4', 'portrait');

// Render the HTML as PDF
$dompdf->render();

// Output the generated PDF to Browser (force download)
$dompdf->stream("mypdf.pdf", [
    "Attachment" => true
]);
}






    /**
    * @Route("/AjouterCertif", name="AjouterCertif")
    */
    public function ajouterCertif(request $request){

        $Certification=new Certification();
        $form=$this->createForm(CertificationType::class,$Certification);
        $form->add('Ajouter', SubmitType::class);
        
        $form->handleRequest($request); 
        if ($form->isSubmitted() && $form->isValid() ) 
        { $Certification = $form->getData();
        $em=$this->getDoctrine()->getManager(); 
        $em->persist($Certification);
        $em->flush();

        
        return $this->redirectToRoute('AfficherCertif');
        }


        return $this->render('Certification/AjouterCertif.html.twig', [
            'form'=>$form->createView()
        ]);
	}


/**
    * @Route("/AfficherCertif", name="AfficherCertif")
    */
    public function AfficherCertif(Request $request){
		
        $repository=$this->getdoctrine()->getrepository(Certification::class);
        $allCertification=$repository->findAll();
         // Paginate the results of the query
         $Certification = $this->get('knp_paginator')->paginate(
            // Doctrine Query, not results
            $allCertification,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            5
        );



        return $this->render('Certification/AfficherCertif.html.twig', [
            'Certifications'=>$Certification,
        ]);
        
    }

    /**
    * @Route("/AfficherCertifFront", name="AfficherCertifFront")
    */
    public function AfficherCertif1(Request $request){
		
        $repository=$this->getdoctrine()->getrepository(Certification::class);
        $Certification=$repository->findAll();
        $allCertification=$repository->findAll();
        // Paginate the results of the query
        $Certification = $this->get('knp_paginator')->paginate(
           // Doctrine Query, not results
           $allCertification,
           // Define the page parameter
           $request->query->getInt('page', 1),
           // Items per page
           5
       );


        return $this->render('Certification/affFront.html.twig', [
            'Certifications'=>$Certification,
        ]);
        
    }








 /**
    * @Route("/ModifierCertif/{id}", name="ModifierCertif")
    */
    public function ModifierCertif(request $request,$id)


        
        {   $em = $this->getDoctrine()->getManager();
            $Certification= $em->getRepository(Certification::class)->find($id);
            $form=$this->createForm(CertificationType::class,$Certification);
            $form->add('modifier',SubmitType::class);
            $form->handleRequest($request);
            if($form->isSubmitted()&& $form->isValid())
            {
               
                $em->flush();
                return $this->redirectToRoute('AfficherCertif');
            }
    

         return $this->render('certification/ModifierCertif.html.twig', [
            'form'=>$form->createView(),
            
        ]);
       
    }




    /**
     * @Route("/SupprimerCertif/{id}", name="SupprimerCertif")
     */
    public function SupprimerCertif($id)

    {
        $em = $this->getDoctrine()->getManager();
        $Certification= $em->getRepository(Certification::class)->find($id);
        $em->remove($Certification);
        $em->flush();
        return $this->redirectToRoute('AfficherCertif');

    }



    /**
     * @Route("/home", name="home")
     */
    public function FrontHome(): Response
    {
        return $this->render('/base2.html.twig', [
          //  'controller_name' => 'CertificationController',
        ]);
    }

 /**
    * @Route("/AfficherCertif ", name="SearchCertification")
    */
   public function searchStudentx(Request $request,NormalizerInterface $Normalizer)
   {
       $repository = $this->getDoctrine()->getRepository(Certification::class);
       $requestString=$request->get('searchValue');
       $Certifications = $repository->findCertifByNom($requestString);
       $jsonContent = $Normalizer->normalize($Certifications, 'json',['groups'=>'Certifications']);
       $retour=json_encode($jsonContent);
       return new Response($retour);
     
   }

   /**
     * @Route("/TrieDate", name="TrieDate")
     */
    public function TrieSelonDate(CertificationRepository $repository)
    {
        $Certifications=$this->getdoctrine()->getRepository(Certification::class)->findByDate();
        return $this->render('certification/AffichageTrieDate.html.twig',[
            'Certifications' => $Certifications,
        ]);

    }

     /**
     * @Route("/JAVA", name="JAVA")
     */
    public function AfficherJava(CertificationRepository $repository)
    {
        $Certifications=$this->getdoctrine()->getRepository(Certification::class)->findByJava();
        return $this->render('certification/AfficherJava.html.twig',[
            'Certifications' => $Certifications,
        ]);

    }
 
    
     /**
     * @Route("/WEB", name="WEB")
     */
    public function AfficherWEB(CertificationRepository $repository)
    {
        $Certifications=$this->getdoctrine()->getRepository(Certification::class)->findByWeb();
        return $this->render('certification/AfficherWeb.html.twig',[
            'Certifications' => $Certifications,
        ]);

    }

    
 



 /**
     * @Route("/stat", name="stat")
     */
public function chartAction(CertificationRepository $repository)

{
  
        $pieChart = new PieChart();
       $Java = 0;
       $Web = 0;
    
    $items= $this->getDoctrine()->getRepository(Certification::class)->findByJava();

    foreach ($items as $item) {
        $Java += 1;
        
    }       

    $items1= $this->getDoctrine()->getRepository(Certification::class)->findByWeb();

    foreach ($items1 as $item) {
        $Web += 1;
        
    }       
         
        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable([
            [ 'JW', 'JAVA/WEB' ],
            [ 'Java', $Java ],
            [ 'Web', $Web ]
        ]);
 
        $pieChart->getOptions()->setTitle('JAVA-WEB CERTIFICATION');
        $pieChart->getOptions()->setHeight(600);
        $pieChart->getOptions()->setWidth(600);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(25);
 
         
        return $this->render('certification/stat.html.twig', array(
                'piechart' => $pieChart,
            )
 
        );
    }
}


