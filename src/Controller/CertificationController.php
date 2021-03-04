<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use App\Entity\Certification;
use App\Repository\CertificationRepository;
use App\Form\CertificationType;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class CertificationController extends AbstractController
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
    public function AfficherCertif(){
		
        $repository=$this->getdoctrine()->getrepository(Certification::class);
        $Certification=$repository->findAll();
        return $this->render('Certification/AfficherCertif.html.twig', [
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

    {    $em = $this->getDoctrine()->getManager();
        $Certification= $em->getRepository(Certification::class)->find($id);
        $em->remove($Certification);
        $em->flush();
        return $this->redirectToRoute('AfficherCertif');


    }




}
