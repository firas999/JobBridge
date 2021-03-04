<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


use App\Entity\DemandeCertification;
use App\Repository\DemandeCertificationRepository;
use App\Form\DemandeCertificationType;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


class DemandeCertificationController extends AbstractController
{
    /**
     * @Route("/demande/certification", name="demande_certification")
     */
    public function index(): Response
    {
        return $this->render('demande_certification/index.html.twig', [
            'controller_name' => 'DemandeCertificationController',
        ]);
    }


    /**
    * @Route("/AjouterDemandeCertification", name="AjouterDemandeCertification")
    */
    public function ajouterDemandeCertif(request $request){

        $DemandeCertification=new DemandeCertification();
        $form=$this->createForm(DemandeCertificationType::class,$DemandeCertification);
        $form->add('Ajouter', SubmitType::class);
        
        $form->handleRequest($request); 
        if ($form->isSubmitted() && $form->isValid() ) 
        { $Certification = $form->getData();


        $em=$this->getDoctrine()->getManager(); 
        $em->persist($DemandeCertification);
        $em->flush();
        return $this->redirectToRoute('AfficherDemandeCertification');
        }


        return $this->render('demande_certification/AjouterDemandeCertification.html.twig', [
            'form'=>$form->createView()
        ]);
	}


/**
    * @Route("/AfficherDemandeCertification", name="AfficherDemandeCertification")
    */
    public function AfficherDemandeCertif(){
		
        $repository=$this->getdoctrine()->getrepository(DemandeCertification::class);
        $DemandeCertification=$repository->findAll();
        return $this->render('demande_certification/AfficherDemandeCertification.html.twig', [
            'DemandesCertifications'=>$DemandeCertification,
        ]);
        
    }


 /**
    * @Route("/ModifierDemandeCertification/{id}", name="ModifierDemandeCertification")
    */
    public function ModifierCertif(request $request,$id)


        
        {   $em = $this->getDoctrine()->getManager();
            $DemandeCertification= $em->getRepository(DemandeCertification::class)->find($id);
            $form=$this->createForm(DemandeCertificationType::class,$DemandeCertification);
            $form->add('modifier',SubmitType::class);
            $form->handleRequest($request);
            if($form->isSubmitted()&& $form->isValid())
            {
               
                $em->flush();
                return $this->redirectToRoute('AfficherDemandeCertification');
            }
    

         return $this->render('demande_certification/ModifierDemandeCertification.html.twig', [
            'form'=>$form->createView(),
            
        ]);
       
    }




    /**
     * @Route("/SupprimerDemandeCertif/{id}", name="SupprimerDemandeCertification")
     */
    public function SupprimerCertif($id)

    {    $em = $this->getDoctrine()->getManager();
        $DemandeCertification= $em->getRepository(DemandeCertification::class)->find($id);
        $em->remove($DemandeCertification);
        $em->flush();
        return $this->redirectToRoute('AfficherDemandeCertification');


    }




}
